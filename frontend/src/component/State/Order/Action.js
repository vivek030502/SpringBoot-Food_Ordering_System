import {
  CREATE_ORDER_REQUEST,
  CREATE_ORDER_SUCCESS,
  CREATE_ORDER_FAILURE,
  GET_USERS_ORDERS_REQUEST,
  GET_USERS_ORDERS_SUCCESS,
  GET_USERS_ORDERS_FAILURE,
} from "./ActionType";
import { api } from "../../config/api";

export const createOrder = (reqData) => {
  return async (dispatch) => {
    dispatch({ type: CREATE_ORDER_REQUEST });
    try {
      const { data } = await api.post("/api/orders", reqData.order, {
        headers: {
          Authorization: `Bearer ${reqData.jwt}`,
        },
      });

      console.log("Created order data:", data);
      dispatch({ type: CREATE_ORDER_SUCCESS, payload: data });

      if (data.payment_url) {
        window.location.href = data.payment_url;
      }
    } catch (error) {
      console.log("Error:", error);
      dispatch({ type: CREATE_ORDER_FAILURE, payload: error });
    }
  };
};

export const getUsersOrders = (jwt) => {
  return async (dispatch) => {
    dispatch({ type: GET_USERS_ORDERS_REQUEST });
    try {
      const { data } = await api.get("/api/orders/user", {
        headers: {
          Authorization: `Bearer ${jwt}`,
        },
      });
      console.log("Users order:", data);
      dispatch({ type: GET_USERS_ORDERS_SUCCESS, payload: data });
    } catch (error) {
      console.log("Error:", error);
      dispatch({ type: GET_USERS_ORDERS_FAILURE, payload: error });
    }
  };
};
