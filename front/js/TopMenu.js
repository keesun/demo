import React from 'react';
import RaisedButton from 'material-ui/RaisedButton';
import {Toolbar, ToolbarGroup, ToolbarSeparator, ToolbarTitle} from 'material-ui/Toolbar';

export default class TopMenu extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
        };
    }

    render() {
        return (
            <Toolbar>
                <ToolbarGroup>
                    <ToolbarTitle text="Demo" />
                    <ToolbarSeparator />
                    <RaisedButton label="Create Event" primary={true} />
                </ToolbarGroup>
            </Toolbar>
        );
    }
}