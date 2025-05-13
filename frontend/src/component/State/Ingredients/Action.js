import {
  GET_INGREDIENTS,
  UPDATE_STOCK,
  CREATE_INGREDIENT_REQUEST,
  CREATE_INGREDIENT_SUCCESS,
  CREATE_INGREDIENT_FAILURE,
  CREATE_INGREDIENT_CATEGORY_REQUEST,
  CREATE_INGREDIENT_CATEGORY_SUCCESS,
  CREATE_INGREDIENT_CATEGORY_FAILURE,
  GET_INGREDIENT_CATEGORY_REQUEST,
  GET_INGREDIENT_CATEGORY_SUCCESS,
  GET_INGREDIENT_CATEGORY_FAILURE,
} from "./ActionType.js";

import { api } from "../../config/api.js";

// Fetch ingredients action
export const getIngredientsOfRestaurant = ({ id, jwt }) => {
  return async (dispatch) => {
    try {
      const response = await api.get(
        `/api/admin/ingredients/restaurant/${id}`,
        {
          headers: {
            Authorization: `Bearer ${jwt}`,
          },
        }
      );

      console.log("Get all ingredients", response.data);

      dispatch({
        type: GET_INGREDIENTS,
        payload: response.data,
      });
    } catch (error) {
      console.error("Error fetching ingredients", error);
    }
  };
};

// Update stock action
export const updateStockOfIngredient = ({ id, jwt }) => {
  return async (dispatch) => {
    try {
      const data = await api.put(
        `/api/admin/ingredients/${id}/stock`,
        {},
        {
          headers: {
            Authorization: `Bearer ${jwt}`,
          },
        }
      );
      console.log("Stock status", data);
      dispatch({ type: UPDATE_STOCK, payload: data.data });
    } catch (error) {
      console.error("Failed to update stock", error);
    }
  };
};

// Create ingredient action
export const createIngredient = ({ data, jwt }) => {
  return async (dispatch) => {
    dispatch({ type: CREATE_INGREDIENT_REQUEST });
    try {
      const response = await api.post("/api/admin/ingredients", data, {
        headers: {
          Authorization: `Bearer ${jwt}`,
        },
      });
      dispatch({ type: CREATE_INGREDIENT_SUCCESS, payload: response.data });
    } catch (error) {
      console.error("Failed to create ingredient", error);
      dispatch({ type: CREATE_INGREDIENT_FAILURE });
    }
  };
};

// Create ingredient category action
export const createIngredientCategory = ({ data, jwt }) => {
  console.log("data", data, "jwt", jwt);
  return async (dispatch) => {
    dispatch({ type: CREATE_INGREDIENT_CATEGORY_REQUEST });
    try {
      const response = await api.post("/api/admin/ingredients/category", data, {
        headers: {
          Authorization: `Bearer ${jwt}`,
        },
      });
      console.log("create ingredients category", response.data);
      dispatch({
        type: CREATE_INGREDIENT_CATEGORY_SUCCESS,
        payload: response.data,
      });
    } catch (error) {
      console.error("Failed to create ingredient category", error);
      dispatch({ type: CREATE_INGREDIENT_CATEGORY_FAILURE });
    }
  };
};

// Get ingredient categories action
export const getIngredientCategory = ({ id, jwt }) => {
  return async (dispatch) => {
    dispatch({ type: GET_INGREDIENT_CATEGORY_REQUEST });
    try {
      const response = await api.get(
        `/api/admin/ingredients/restaurant/${id}/category`,
        {
          headers: {
            Authorization: `Bearer ${jwt}`,
          },
        }
      );
      console.log("get ingredients category", response.data);
      dispatch({
        type: GET_INGREDIENT_CATEGORY_SUCCESS,
        payload: response.data,
      });
    } catch (error) {
      console.error("Failed to fetch ingredient categories", error);
      dispatch({ type: GET_INGREDIENT_CATEGORY_FAILURE });
    }
  };
};
