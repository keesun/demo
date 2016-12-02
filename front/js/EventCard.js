import React from 'react';
import {Card, CardActions, CardHeader, CardMedia, CardTitle, CardText} from 'material-ui/Card';
import FlatButton from 'material-ui/FlatButton';

const EventCard = () => (
    <Card>
        <CardHeader
            title="백기선"
            subtitle="Keesun A.K.A Whiteship"
            avatar="/images/avatar/keesun.jpeg"
        />
        <CardMedia
            overlay={<CardTitle title="박근혜 하야 촉구 촛불집회" subtitle="박근혜는 하야하라. 새누리당 해체하라." />}
        >
            <img src="/images/candles-492171_640.jpg" />
        </CardMedia>
        <CardText>
            집회에 참석하거나 또는 직접 참석하지 못하는 분들도 이곳에서 모여 자신의 생각과 의견을 공유할 수 있습니다. <br/>
            접속하시면 전체 참석자 수를 확인할 수 있으며, 모임장의 공지 사항을 확인할 수 있습니다. <br/>
            또한, 맵에서 전체 참석자 위치를 확인할 수도 있습니다. 참석자의 위치는 주황색이며 모임장의 위치는 붉은색입니다.
        </CardText>
        <CardActions>
            <FlatButton label="Join" />
            <FlatButton label="Share" />
        </CardActions>
    </Card>
);

export default EventCard;
