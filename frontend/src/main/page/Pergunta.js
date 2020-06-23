import React, { useState, useEffect } from 'react'
import {
    Link,
    useParams
} from "react-router-dom";


export default function Pergunta() {

    let { id } = useParams();
    const [ pergunta, setPergunta ] = useState({});
    const [ respostas, setRespostas ] = useState([]);

    const loadData = () => {

    };

    return (
        <div>
            <Link to="/">Voltar</Link>
            <div>

            </div>
        </div>
    )
}

