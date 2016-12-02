import React from 'react';
import EventCard from './EventCard';
import {List, ListItem} from 'material-ui/List';

export default class EventList extends React.Component {
    render() {
        return (
            <List>
                <EventCard />
                <EventCard />
            </List>
        );
    }
}