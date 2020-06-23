import React from 'react'
import {
    Container, 
    Row, 
    Col,
} from 'react-bootstrap';
import '../style/CredenciamentoHeader.css';

export default function CredenciamentoHeader(props) {
    return (
        <Row className="auth-header py-2">
            <Col className="ml-auto">
                <Button className="auth-button mr-2" onClick={ props.onLogin }>Login</Button>
                <Button className="auth-button" onClick={ props.onCadastro }>Cadastrar</Button>
            </Col>
        </Row>
    )
}
