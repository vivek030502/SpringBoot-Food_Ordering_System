import React from "react";
import HomeIcon from "@mui/icons-material/Home";
import { Button, Card } from "@mui/material";

export const AddressCard = ({ item, showButton, handleSelectedAddress }) => {
  return (
    <Card className="flex gap-5 w-64 p-5">
      <HomeIcon />I
      <div className="space-y-3text-gray-500">
        <h1 className="font-semibold text-lg text-white">Home</h1>
        <p>
          Mumbai, new shivam building, gokuldham market, 534567, Maharashtra,
          India
        </p>
        {showButton && (
          <Button
            variant="outlined"
            fullWidth
            onClick={() => handleSelectedAddress(item)}
          >
            select
          </Button>
        )}
      </div>
    </Card>
  );
};
