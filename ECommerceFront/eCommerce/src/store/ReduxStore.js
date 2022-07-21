import {configureStore} from "@reduxjs/toolkit";
import authReducer from './AuthReducers.js'
import basketReducer from './BasketReducer.js'
export const store = configureStore({
    reducer : {
        auth : authReducer,
        basket : basketReducer
    }
});

