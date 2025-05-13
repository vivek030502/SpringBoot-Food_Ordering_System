import { Badge, Box, IconButton } from "@mui/material";
import React from "react";
import SearchIcon from "@mui/icons-material/Search";
import Avatar from "@mui/material/Avatar";
import { pink } from "@mui/material/colors";
import ShoppingCartIcon from "@mui/icons-material/ShoppingCart";
import "./Navbar.css";
import { Person } from "@mui/icons-material";
import { useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";

export const Navbar = () => {
  const { auth, cart } = useSelector((store) => store);
  const navigate = useNavigate();

  const handleAvtarClick = () => {
    if (auth.user?.role === "ROLE_CUSTOMER") {
      navigate("/my-profile");
    } else {
      navigate("/my-profile");
    }
  };

  return (
    <Box
      className="px-5 py-[.8rem] lg:px-20 flex justify-between"
      sx={{
        position: "fixed",
        top: 0,
        left: 0,
        right: 0,
        zIndex: 1300,
        width: "100%",
        backgroundColor: "#472121",
      }}
    >
      <div className="lg:mr-10 cursor-pointer flex items-center space-x-4">
        <li
          onClick={() => navigate("/")}
          className="logo font-semibold text-gray-300 text-2xl"
        >
          Dil Se Rasoi
        </li>
      </div>

      <div className="flex flex-row items-center space-x-4 lg:space-x-10">
        <div>
          <IconButton>
            <SearchIcon sx={{ fontSize: "1.5rem" }} />
          </IconButton>
        </div>

        <div>
          {auth.user?.fullname ? (
            <Avatar
              onClick={handleAvtarClick}
              sx={{ bgcolor: "white", color: pink.A400 }}
            >
              {auth.user.fullname.charAt(0).toUpperCase()}
            </Avatar>
          ) : (
            <IconButton onClick={() => navigate("/account/login")}>
              <Person />
            </IconButton>
          )}
        </div>

        <div>
          <IconButton onClick={() => navigate("/cart")}>
            <Badge color="primary" badgeContent={cart.cart?.items.length}>
              <ShoppingCartIcon sx={{ fontSize: "1.5rem" }} />
            </Badge>
          </IconButton>
        </div>
      </div>
    </Box>
  );
};
