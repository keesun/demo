'use strict';

import React from 'react';
import ReactDOM from 'react-dom';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import TopMenu from './TopMenu';
import EventList from './EventList';

import injectTapEventPlugin from 'react-tap-event-plugin';

// Needed for onTouchTap
// http://stackoverflow.com/a/34015469/988941
injectTapEventPlugin();

const App = () => (
    <MuiThemeProvider>
        <div>
            <TopMenu />
            <EventList />
        </div>
    </MuiThemeProvider>
);

ReactDOM.render(<App />, document.getElementById('react'));
