import { Client } from '@stomp/stompjs';
import SockJS from 'sockjs-client';

let stompClient = null;

/**
 * Function to establish WebSocket connection and subscribe to a session
 * @param {string} sessionId - ID of the quiz session to subscribe to
 * @param {function} callback - Callback function to handle incoming messages
 */
export const connect = (sessionId, callback) => {
    const socket = new SockJS('/quiz-websocket');
    stompClient = new Client({
        webSocketFactory: () => socket,
        reconnectDelay: 5000,
        heartbeatIncoming: 4000,
        heartbeatOutgoing: 4000,
        onConnect: () => {
            console.log('Connected');
            stompClient.subscribe('/topic/questions/' + sessionId, message => {
                callback(JSON.parse(message.body));
            });
        }
    });
    stompClient.activate();
};

/**
 * Function to send player's answer to the server
 * @param {string} sessionId - ID of the quiz session
 * @param {string} playerName - Name of the player
 * @param {string} answer - Player's answer
 */
export const sendAnswer = (sessionId, playerName, answer) => {
    if (stompClient && stompClient.connected) {
        const playerAnswer = {
            sessionId: sessionId,
            playerName: playerName,
            answer: answer
        };
        stompClient.publish({
            destination: "/app/quiz/answer",
            body: JSON.stringify(playerAnswer)
        });
    }
};

/**
 * Function to disconnect the WebSocket connection
 */
export const disconnect = () => {
    if (stompClient !== null) {
        stompClient.deactivate();
    }
    console.log("Disconnected");
};
