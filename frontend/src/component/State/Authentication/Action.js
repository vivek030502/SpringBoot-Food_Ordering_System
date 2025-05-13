import axios from "axios";
import { api, API_URL } from "../../config/api";

import {
  REGISTER_REQUEST,
  REGISTER_SUCCESS,
  REGISTER_FAILURE,
  LOGIN_REQUEST,
  LOGIN_SUCCESS,
  LOGIN_FAILURE,
  GET_USER_REQUEST,
  GET_USER_SUCCESS,
  GET_USER_FAILURE,
  LOGOUT,
  ADD_TO_FAVOURITE_REQUEST,
  ADD_TO_FAVOURITE_SUCCESS,
  ADD_TO_FAVOURITE_FAILURE,
} from "./ActionType";

// REGISTER
export const registerUser = (reqData) => async (dispatch) => {
  dispatch({ type: REGISTER_REQUEST });
  try {
    const { data } = await axios.post(
      `${API_URL}/auth/signup`,
      reqData.userData
    );
    if (data.jwt) localStorage.setItem("jwt", data.jwt);

    if (data.role === "ROLE_RESTAURANT_OWNER") {
      reqData.navigate("/admin/restaurants");
    } else {
      reqData.navigate("/");
    }

    dispatch({ type: REGISTER_SUCCESS, payload: data.jwt });
    console.log("register success", data);
  } catch (error) {
    dispatch({ type: REGISTER_FAILURE, payload: error });
    console.log("Registration failed: " + error.message);
  }
};

// LOGIN
export const loginUser = (reqData) => async (dispatch) => {
  dispatch({ type: LOGIN_REQUEST });
  try {
    const { data } = await axios.post(
      `${API_URL}/auth/login`,
      reqData.userData
    );
    if (data.jwt) localStorage.setItem("jwt", data.jwt);

    if (data.role === "ROLE_RESTAURANT_OWNER") {
      reqData.navigate("/admin/restaurants");
    } else {
      reqData.navigate("/");
    }

    dispatch({ type: LOGIN_SUCCESS, payload: data.jwt });
    console.log("login success", data);
  } catch (error) {
    dispatch({ type: LOGIN_FAILURE, payload: error });
    console.log("Login failed: " + error.message);
  }
};

// GET USER
export const getUser = (jwt) => async (dispatch) => {
  dispatch({ type: GET_USER_REQUEST });
  try {
    const jwt = localStorage.getItem("jwt");

    const { data } = await api.get(`/api/users/profile`, {
      headers: {
        Authorization: `Bearer ${jwt}`,
      },
    });

    dispatch({ type: GET_USER_SUCCESS, payload: data });
    console.log("user profile", data);
  } catch (error) {
    dispatch({ type: GET_USER_FAILURE, payload: error });
    console.log("Fetching user failed: " + error.message);
  }
};

// Add to favourite
export const addToFavourite =
  ({ jwt, restaurantId }) =>
  async (dispatch) => {
    dispatch({ type: ADD_TO_FAVOURITE_REQUEST });
    try {
      const jwt = localStorage.getItem("jwt");

      const { data } = await api.put(
        `/api/restaurants/${restaurantId}/add-favourites`,
        {},
        {
          headers: {
            Authorization: `Bearer ${jwt}`,
          },
        }
      );

      dispatch({ type: ADD_TO_FAVOURITE_SUCCESS, payload: data });
      console.log("added to favourites", data);
    } catch (error) {
      dispatch({ type: ADD_TO_FAVOURITE_FAILURE, payload: error });
      console.log("Adding to favourite failed: " + error);
    }
  };

// LOGOUT
export const logout = () => async (dispatch) => {
  try {
    localStorage.clear();
    dispatch({ type: LOGOUT });
    console.log("logout success");
  } catch (error) {
    console.log("Logout failed: " + error.message);
  }
};
