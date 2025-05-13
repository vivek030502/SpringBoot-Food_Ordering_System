import { api } from "../../config/api.js";
import {
  GET_RESTAURANTS_ORDER_SUCCESS,
  GET_RESTAURANTS_ORDER_REQUEST,
  GET_RESTAURANTS_ORDER_FAILURE,
  UPDATE_ORDER_STATUS_REQUEST,
  UPDATE_ORDER_STATUS_SUCCESS,
  UPDATE_ORDER_STATUS_FAILURE,
} from "./ActionType.js";

export const updateOrderStatus = ({ orderId, orderStatus, jwt }) => {
  return async (dispatch) => {
    try {
      dispatch({ type: UPDATE_ORDER_STATUS_REQUEST });

      const response = await api.put(
        `/api/admin/orders/${orderId}/${orderStatus}`,
        {},
        {
          headers: {
            Authorization: `Bearer ${jwt}`,
          },
        }
      );

      const updatedOrder = response.data;
      console.log("updated order", updatedOrder);
      dispatch({
        type: UPDATE_ORDER_STATUS_SUCCESS,
        payload: updatedOrder,
      });
    } catch (error) {
      console.error("Failed to update order status", error);
      dispatch({ type: UPDATE_ORDER_STATUS_FAILURE, payload: error });
    }
  };
};

export const fetchRestaurantsOrder = ({ restaurantId, orderStatus, jwt }) => {
  return async (dispatch) => {
    try {
      dispatch({ type: GET_RESTAURANTS_ORDER_REQUEST });

      const { data } = await api.get(
        `/api/admin/orders/restaurant/${restaurantId}`,
        {
          params: { order_status: orderStatus },
          headers: {
            Authorization: `Bearer ${jwt}`,
          },
        }
      );

      const orders = data;
      console.log("restaurant order", orders);
      dispatch({
        type: GET_RESTAURANTS_ORDER_SUCCESS,
        payload: orders,
      });
    } catch (error) {
      console.error("Failed to fetch restaurant orders", error);
      dispatch({ type: GET_RESTAURANTS_ORDER_FAILURE });
    }
  };
};
