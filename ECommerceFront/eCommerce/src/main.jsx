import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App'
import './index.css'
import {store} from './store/ReduxStore.js'
import {Provider} from "react-redux";
import {ThemeProvider} from "@mui/material";
import {theme} from "./styles/ThemeProvider.js";
import {BrowserRouter} from "react-router-dom";

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <Provider store={store}>
        <BrowserRouter>
        <ThemeProvider theme={theme}>
            <App/>
        </ThemeProvider>
        </BrowserRouter>
    </Provider>

  </React.StrictMode>
)
