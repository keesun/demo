import React from 'react';
import AppBar from 'material-ui/AppBar';
import FlatButton from 'material-ui/FlatButton';

class TopMenu extends React.Component {
    render() {
        return (
            <div>
                <AppBar title="Demo" iconElementRight={<FlatButton label="Login" />} />
            </div>
        );
    }
}

export default TopMenu;
