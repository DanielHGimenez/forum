import React, { useState, useEffect } from 'react';
import {
    Modal, 
    Form,
} from 'react-bootstrap';
import Button from './Button';
import '../style/ModalCredenciamento.css';

export default function ModalCredenciamento(props) {

    const [ usuario, setUsuario ] = useState("");
    const [ senha, setSenha ] = useState("");

    const changeUsuario = (event) => {
        setUsuario(event.target.value);
    };
    const changeSenha = (event) => {
        setSenha(event.target.value);
    };

    const onSubmit = () => {
        if (usuario && senha) {
            props.onSubmit(usuario, senha);
        }
    };

    return (
        <Modal className="modal-credenciamento" show={ props.show } onHide={ props.onHide } >
            <Modal.Header closeButton />
            <Modal.Body>
                <Form>
                    <Form.Group>
                        <Form.Label>Usuario</Form.Label>
                        <Form.Control type="text" placeholder="Nome de usuario" onChange={ changeUsuario } />
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Senha</Form.Label>
                        <Form.Control type="password" placeholder="Senha" onChange={ changeSenha } />
                    </Form.Group>
                </Form>
            </Modal.Body>
            <Modal.Footer>
                <Button className="enviar" onClick={ onSubmit }>{ props.submitText }</Button>
            </Modal.Footer>
        </Modal>
    )
}
