import React, { useEffect } from "react";
import { OrderCard } from "./OrderCard";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { getUsersOrders } from "../State/Order/Action";

const Orders = () => {
  const { auth, cart, order } = useSelector((store) => store);
  const navigate = useNavigate();
  const jwt = localStorage.getItem("jwt");
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(getUsersOrders(jwt));
  }, [auth.jwt]);
  return (
    <div className="flex flex-col items-center w-full px-2 min-h-screen bg-black">
      <h1 className="text-xl text-center py-7 font-semibold mt-20">
        My Orders
      </h1>
      <div className="space-y-5 w-full lg:w-1/2">
        {order.orders.map((order) =>
          order.items.map((item) => <OrderCard order={order} item={item} />)
        )}
      </div>
    </div>
  );
};

export default Orders;
