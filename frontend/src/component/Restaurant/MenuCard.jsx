import {
  Accordion,
  AccordionDetails,
  AccordionSummary,
  FormGroup,
  FormControlLabel,
  Checkbox,
  Button,
} from "@mui/material";
import React, { useState } from "react";
import ExpandMoreIcon from "@mui/icons-material/ExpandMore";
import { CategorizeIngredients } from "../Util/CategorizeIngredients";
import { useDispatch } from "react-redux";
import { addItemToCart } from "../State/Cart/Action";

const demo = [
  {
    category: "Nuts & seeds",
    ingredients: ["Cashews"],
  },
  {
    category: "Protein",
    ingredients: ["Ground beef", "Bacon strips"],
  },
];

const MenuCard = ({ item }) => {
  const [selectedIngredients, setSelectedIngredients] = useState([]);

  const dispatch = useDispatch();

  const handleCheckBoxChange = (itemName) => {
    console.log("value", itemName);
    if (selectedIngredients.includes(itemName)) {
      setSelectedIngredients(
        selectedIngredients.filter((item) => item !== itemName)
      );
    } else {
      setSelectedIngredients([...selectedIngredients, itemName]);
    }
  };

  const handleAddItemToCart = (e) => {
    e.preventDefault();
    const reqData = {
      foodId: item.id,
      quantity: 1,
      ingredients: selectedIngredients,
      token: localStorage.getItem("jwt"),
    };
    dispatch(addItemToCart(reqData));
    console.log("req data", reqData);
  };

  return (
    <Accordion>
      <AccordionSummary
        expandIcon={<ExpandMoreIcon />}
        aria-controls="panel1-content"
        id="panel1-header"
      >
        <div className="lg:flex items-center justify-between">
          <div className="lg:flex items-center gap-5">
            <img
              className="w-[7rem] h-[7rem] object-cover"
              src={item.images[0]}
              alt="Menu item"
            />
            <div className="space-y-1 lg:space-y-5 lg:max-w-xl">
              <p className="font-semibold text-xl">{item.name}</p>
              <p>₹{item.price}</p>
              <p className="text-gray-400">{item.description}</p>
            </div>
          </div>
        </div>
      </AccordionSummary>
      <AccordionDetails>
        <form onSubmit={handleAddItemToCart}>
          <div className="flex gap-5 flex-wrap">
            {Object.keys(CategorizeIngredients(item.ingredients)).map(
              (category) => (
                <div key={item.category}>
                  <p className="font-semibold">{category}</p>
                  <FormGroup>
                    {CategorizeIngredients(item.ingredients)[category].map(
                      (item) => (
                        <FormControlLabel
                          key={item.id}
                          control={
                            <Checkbox
                              onChange={() => handleCheckBoxChange(item.name)}
                            />
                          }
                          label={item.name}
                        />
                      )
                    )}
                    {/* <FormControlLabel
                      required
                      control={<Checkbox />}
                      label="Required"
                    />
                    <FormControlLabel
                      disabled
                      control={<Checkbox />}
                      label="Disabled"
                    /> */}
                  </FormGroup>
                </div>
              )
            )}
          </div>
          <div className="pt-5">
            <Button variant="contained" disabled={false} type="submit">
              Add to Cart
            </Button>
          </div>
        </form>
      </AccordionDetails>
    </Accordion>
  );
};

export default MenuCard;
