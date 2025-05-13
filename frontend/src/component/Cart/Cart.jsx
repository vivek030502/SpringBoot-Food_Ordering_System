import {
  Divider,
  Button,
  Card,
  Modal,
  Box,
  Grid,
  TextField,
} from "@mui/material";
import React from "react";
import CartItem from "./CartItem";
import { AddressCard } from "./AddressCard";
import { AddLocation } from "@mui/icons-material";
import { Formik, Form, Field } from "formik"; // npm i formik
import * as Yup from "yup"; // npm i yup
import { useDispatch, useSelector } from "react-redux";
import { createOrder } from "../State/Order/Action";

export const style = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 400,
  bgcolor: "background.paper",
  outline: "none",
  boxShadow: 24,
  p: 4,
};

const initialValues = {
  streetAddress: "",
  state: "",
  pincode: "",
  city: "",
};

const validationSchema = Yup.object({
  streetAddress: Yup.string().required("Street address is required"),
  state: Yup.string().required("State is required"),
  pincode: Yup.string().required("Pincode is required"),
  city: Yup.string().required("City is required"),
});

const Cart = () => {
  const [open, setOpen] = React.useState(false);
  const { cart, auth } = useSelector((store) => store);
  const dispatch = useDispatch();

  const createOrderUsingSelectedAddress = () => {};
  const handleOpenAddressModal = () => setOpen(true);
  const handleClose = () => setOpen(false);

  const handleSubmit = (values) => {
    const data = {
      jwt: localStorage.getItem("jwt"),
      order: {
        restaurantId: cart.cartItems[0].food?.restaurant.id,
        deliveryAddress: {
          fullName: auth.user?.fullName,
          streetAddress: values.streetAddress,
          city: values.city,
          state: values.state,
          postalCode: values.pincode,
          country: "India",
        },
      },
    };
    dispatch(createOrder(data));
    console.log("Form values submitted:", values);
  };

  return (
    <div>
      <main className="lg:flex justify-between">
        {/* Left Section - Cart Items and Bill */}
        <section className="lg:w-[30%] space-y-6 lg:min-h-screen pt-10">
          {cart.cartItems.map((item) => (
            <CartItem item={item} />
          ))}
          <Divider />
          <div className="billDetails px-5 text-sm">
            <p className="font-extralight py-5">Bill Details</p>
            <div className="space-y-3">
              <div className="flex justify-between text-gray-400">
                <p>Item Total</p>
                <p>₹{cart.cart?.total}</p>
              </div>
              <div className="flex justify-between text-gray-400">
                <p>Delivery Fee</p>
                <p>₹21</p>
              </div>
              <div className="flex justify-between text-gray-400">
                <p>GST and Restaurant Charges</p>
                <p>₹33</p>
              </div>
              <Divider />
            </div>
            <div className="flex justify-between text-gray-400 pt-3">
              <p>Total Pay</p>
              <p>₹{cart.cart?.total + 21 + 33}</p>
            </div>
          </div>
        </section>

        {/* Right Section - Address Selection */}
        <Divider orientation="vertical" flexItem />
        <section className="lg:w-[70%] flex justify-center px-5 pb-10 lg:pb-0 mt-20">
          <div>
            <h1 className="text-center font-semibold text-2xl py-10">
              Choose Delivery Address
            </h1>
            <div className="flex gap-5 flex-wrap justify-center">
              {[1, 1, 1, 1, 1].map((item, index) => (
                <AddressCard
                  key={index}
                  handleSelectedAddress={createOrderUsingSelectedAddress}
                  item={item}
                  showButton={true}
                />
              ))}

              <Card className="flex flex-col items-center gap-5 w-64 p-5">
                <AddLocation />
                <div className="space-y-3 text-gray-500 text-center">
                  <h1 className="font-semibold text-lg">Add New Address</h1>
                  <Button
                    variant="outlined"
                    fullWidth
                    onClick={handleOpenAddressModal}
                  >
                    Add
                  </Button>
                </div>
              </Card>
            </div>
          </div>
        </section>
      </main>

      {/* Address Modal */}
      <Modal open={open} onClose={handleClose}>
        <Box sx={style}>
          <Formik
            initialValues={initialValues}
            validationSchema={validationSchema}
            onSubmit={handleSubmit}
          >
            {() => (
              <Form>
                <Grid container spacing={2}>
                  <Grid item xs={12}>
                    <Field name="streetAddress">
                      {({ field, meta }) => (
                        <TextField
                          {...field}
                          label="Street Address"
                          fullWidth
                          variant="outlined"
                          error={meta.touched && Boolean(meta.error)}
                          helperText={meta.touched && meta.error}
                        />
                      )}
                    </Field>
                  </Grid>
                  <Grid item xs={12}>
                    <Field name="state">
                      {({ field, meta }) => (
                        <TextField
                          {...field}
                          label="State"
                          fullWidth
                          variant="outlined"
                          error={meta.touched && Boolean(meta.error)}
                          helperText={meta.touched && meta.error}
                        />
                      )}
                    </Field>
                  </Grid>
                  <Grid item xs={12}>
                    <Field name="city">
                      {({ field, meta }) => (
                        <TextField
                          {...field}
                          label="City"
                          fullWidth
                          variant="outlined"
                          error={meta.touched && Boolean(meta.error)}
                          helperText={meta.touched && meta.error}
                        />
                      )}
                    </Field>
                  </Grid>
                  <Grid item xs={12}>
                    <Field name="pincode">
                      {({ field, meta }) => (
                        <TextField
                          {...field}
                          label="Pin Code"
                          fullWidth
                          variant="outlined"
                          error={meta.touched && Boolean(meta.error)}
                          helperText={meta.touched && meta.error}
                        />
                      )}
                    </Field>
                  </Grid>
                  <Grid item xs={12}>
                    <Button
                      fullWidth
                      variant="contained"
                      type="submit"
                      color="primary"
                    >
                      Deliver Here
                    </Button>
                  </Grid>
                </Grid>
              </Form>
            )}
          </Formik>
        </Box>
      </Modal>
    </div>
  );
};

export default Cart;
