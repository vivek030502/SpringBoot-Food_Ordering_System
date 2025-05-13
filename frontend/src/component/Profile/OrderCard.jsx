import { Button, Card } from "@mui/material";
import React from "react";

export const OrderCard = ({ item, order }) => {
  return (
    <Card
      sx={{
        display: "flex",
        justifyContent: "space-between",
        alignItems: "center",
        padding: 2,
        backgroundColor: "#1f1f1f", // Dark background for card
        color: "white",
      }}
    >
      <div className="flex items-center space-x-5">
        <img className="h-16 w-16 rounded" src={item.food?.images[0]} alt="" />
        <div>
          <p className="font-semibold text-lg">{item.food?.name}</p>
          <p className="text-gray-300">${item.totalPrice}</p>
        </div>
      </div>
      <div>
        <Button
          sx={{
            color: "#f91880",
            fontWeight: "bold",
            textTransform: "uppercase",
          }}
          //disabled
        >
          {order.orderStatus}
        </Button>
      </div>
    </Card>
  );
};
