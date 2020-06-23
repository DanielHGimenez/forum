import React, { useState, useEffect } from 'react';
import { Modal, Form } from 'react-bootstrap';
import '../style/Principal.css';
import Api from '../service/Api';


export default function Principal() {

    const [ publicarPergunta, setPublicarPergunta ] = useState(false);

    return (
        <div className="Principal">
            <Modal show={ publicarPergunta }>
                <Form>
                    <Form.Label>Digite a sua pergunta:</Form.Label>
                    <Form.Control as="textarea" rows="3" />
                </Form>
            </Modal>
        </div>
    )
}
