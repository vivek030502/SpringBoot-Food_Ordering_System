import {
  Card,
  FormControl,
  FormControlLabel,
  Radio,
  RadioGroup,
  Typography,
} from "@mui/material";
import React from "react";
import { useState } from "react";
import OrderTable from "./OrderTable";

const orderStatus = [
  { label: "Pending", value: "PENDING" },
  { label: "Completed", value: "COMPLETED" },
  { label: "All", value: "ALL" },
];

const Orders = () => {
  const [filterValue, setFilterValue] = useState(null);

  const handleFilter = (e, value) => {
    setFilterValue(value);
  };

  return (
    <div>
      <Card className="p-5">
        <Typography sx={{ paddingBottom: "1rem" }} variant="h5">
          Order Status
        </Typography>

        <FormControl>
          <RadioGroup
            row
            name="category"
            onChange={handleFilter}
            value={filterValue || "all"}
          >
            {orderStatus.map((item) => (
              <FormControlLabel
                key={item.value}
                value={item.value}
                control={<Radio />}
                label={item.label}
                sx={{ color: "gray" }}
              />
            ))}
          </RadioGroup>
        </FormControl>
      </Card>
      <OrderTable />
    </div>
  );
};

export default Orders;
