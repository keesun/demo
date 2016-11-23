'use strict';

import React from 'react';
import ReactDOM from 'react-dom';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import TopMenu from './TopMenu';

const App = () => (
    <MuiThemeProvider>
        <TopMenu />
    </MuiThemeProvider>
);

ReactDOM.render(<App />, document.getElementById('react'));
