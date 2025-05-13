// import logo from './logo.svg';
import "./App.css";
import { ThemeProvider } from "@emotion/react";
import { darkTheme } from "./Theme/DarkTheme";
import { CssBaseline } from "@mui/material";
import Home from "./component/Home/Home";
import Cart from "./component/Cart/Cart";
import RestaurantDetails from "./component/Restaurant/RestaurantDetails";
import { CustomerRoute } from "./Routers/CustomerRoute";
import Profile from "./component/Profile/Profile";
import { getUser } from "./component/State/Authentication/Action";
import { useDispatch, useSelector } from "react-redux";
import { useEffect } from "react";
import { findCart } from "./component/State/Cart/Action";
import Routers from "./Routers/Routers";
import { getRestaurantsByUserId } from "./component/State/Restaurant/Action";

function App() {
  const dispatch = useDispatch();
  const jwt = localStorage.getItem("jwt");
  const { auth } = useSelector((store) => store);
  useEffect(() => {
    dispatch(getUser(auth.jwt || jwt));
    dispatch(findCart(jwt));
  }, [auth.jwt]);

  useEffect(() => {
    dispatch(getRestaurantsByUserId(auth.jwt || jwt));
  }, [auth.user]);
  return (
    <ThemeProvider theme={darkTheme}>
      <CssBaseline />
      {/* <CustomerRoute /> */}
      <Routers />
    </ThemeProvider>
  );
}

export default App;
