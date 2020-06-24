import React, { useState } from 'react';
import { Modal, Form } from 'react-bootstrap';
import Button from './Button';
import StringUtil from '../util/StringUtil';
import '../style/ModalPublicarPergunta.css';

export default function ModalPublicarPergunta({ show, onHide, onSubmit }) {
    
    const [ pergunta, setPergunta ] = useState("");

    const changePergunta = (event) => {
        setPergunta(event.target.value);
    };

    const handleSubmit = () => {
        if (StringUtil.isBlank(pergunta)) {
            onSubmit(pergunta);
        }
    };
    
    return (
        <Modal appName="modal-publicar-pergunta" show={ show } onHide={ onHide }>
            <Modal.Header closeButton />
            <Modal.Body>
                <Form>
                    <Form.Label>Digite a sua pergunta:</Form.Label>
                    <Form.Control as="textarea" rows="3" onChange={ changePergunta } />
                </Form>
            </Modal.Body>
            <Modal.Footer>
                <Button className="enviar" onClick={ handleSubmit }>Enviar</Button>
            </Modal.Footer>
        </Modal>
    )
}
