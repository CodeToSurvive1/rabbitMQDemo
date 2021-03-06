#!/usr/bin/env node

var amqp = require('amqplib/callback_api');

amqp.connect('amqp://localhost', function(err, conn) {
    conn.createChannel(function(err, ch) {
        var q = "hello";
        ch.assertQueue(q, { durable: false });
        console.log("waiting for messages");

        ch.consume(q, function(msg) {
            console.log(" received message %s", msg.content.toString());
        }, { noAck: true });
    })
})