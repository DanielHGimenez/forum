import React, { useState } from 'react';
import { Modal, Form } from 'react-bootstrap';
import Button from './Button';
import StringUtil from '../util/StringUtil';
import '../style/ModalPublicarTexto.css';

export default function ModalPublicarTexto({ show, onHide, onSubmit, label }) {
    
    const [ texto, setTexto ] = useState("");

    const changeTexto = (event) => {
        setTexto(event.target.value);
    };

    const handleSubmit = () => {
        if (StringUtil.isBlank(texto)) {
            onSubmit(texto);
        }
    };
    
    return (
        <Modal className="Modal-publicar-texto" show={ show } onHide={ onHide }>
            <Modal.Header closeButton />
            <Modal.Body>
                <Form>
                <Form.Label>{ label }</Form.Label>
                    <Form.Control as="textarea" rows="3" onChange={ changeTexto } />
                </Form>
            </Modal.Body>
            <Modal.Footer>
                <Button className="enviar" onClick={ handleSubmit }>Enviar</Button>
            </Modal.Footer>
        </Modal>
    )
}
