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
} from './ActionType'; 
import { isPresentInFavourites } from '../../config/logic';


const initialState = {
  user: null,
  isLoading: false,
  error: null,
  jwt: null,
  favourites: [],
  success: null,
};

export const authReducer = (state = initialState, action) => {
  switch (action.type) {
    case REGISTER_REQUEST:
    case LOGIN_REQUEST:
    case GET_USER_REQUEST:
    case ADD_TO_FAVOURITE_REQUEST:
      return { ...state, isLoading: true, error: null, success: null };

    case REGISTER_SUCCESS:   
    case LOGIN_SUCCESS:
      return {
        ...state,
        isLoading: false,
        jwt: action.payload,
        success: "Register success",
      };

    case ADD_TO_FAVOURITE_SUCCESS:
      return {
        ...state,
        isLoading: false,
        error: null,
        favourites: isPresentInFavourites(state.favourites, action.payload)
        ? state.favourites.filter((item)=> item.id!==action.payload.id)
        : [action.payload, ...state.favourites]
      };

      case GET_USER_SUCCESS:
        return {
          ...state,
          isLoading: false,
          user: action.payload,
          favourites: action.payload.favourites
        };

      case LOGOUT:
        return initialState;

    case REGISTER_FAILURE:
    case LOGIN_FAILURE:
    case GET_USER_FAILURE:
    case ADD_TO_FAVOURITE_FAILURE:
          return { ...state, isLoading: false, error: action.payload, success: null };

    default:
      return state;  
  }
};
