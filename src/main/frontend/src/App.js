import React, { useState, useEffect } from 'react';
import { connect, sendAnswer, disconnect } from './WebSocketService';

const App = () => {
    const [question, setQuestion] = useState(null);
    const [sessionId, setSessionId] = useState("");
    const [playerName, setPlayerName] = useState("");

    useEffect(() => {
        if (sessionId) {
            // jak nie ma jeszcze session id to dodajemy sie do jakiegos session
            connect(sessionId, setQuestion);
            return () => disconnect();
        }
        // jak juz mamy session id to po prostu pobieramy pytanie i wyswietlamy je czyli mamy wypelnic tu zmienna question
    }, [sessionId]);

    const handleAnswer = (answer) => {
        sendAnswer(sessionId, playerName, answer);
    };

    const handleJoinSession = () => {
        // Implement logic to join a session, for example, setting the sessionId and playerName
    };

    return (
        <div>
            <div>
                <input
                    type="text"
                    placeholder="Session ID"
                    value={sessionId}
                    onChange={(e) => setSessionId(e.target.value)}
                />
                <input
                    type="text"
                    placeholder="Player Name"
                    value={playerName}
                    onChange={(e) => setPlayerName(e.target.value)}
                />
                <button onClick={handleJoinSession}>Join Session</button>
            </div>

            {question && (
                <div>
                    <h1>{question.text}</h1>
                    <ul>
                        {question.answers.map((ans, index) => (
                            <li key={index} onClick={() => handleAnswer(ans)}>{ans}</li>
                        ))}
                    </ul>
                </div>
            )}
        </div>
    );
};

export default App;
