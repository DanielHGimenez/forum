import React, { useState, useEffect } from 'react';
import {
    Row, 
    Col,
} from 'react-bootstrap';
import Button from './Button';
import ModalCredenciamento from './ModalCredenciamento';
import Api from '../service/Api';
import { CredenciaisActions as Actions } from '../reducer/CredenciaisActions.js';
import '../style/CredenciamentoHeader.css';

export default function CredenciamentoHeader({ store }) {

    const [ isLogin, setIsLogin ] = useState(false);
    const [ isCadastro, setIsCadastro ] = useState(false);
    const [ isAutenticado, setIsAutenticado ] = useState(false);

    store.subscribe(() => {
        if (store.getState().credenciais != null) {
            setIsAutenticado(true);
        } else {
            localStorage.removeItem('credenciais');
            setIsAutenticado(false);
        }
    });

    useEffect(() => {
        let credenciais = localStorage.getItem('credenciais');
        if (credenciais) {
            store.dispatch({
                type: Actions.ADICIONAR_CREDENCIAIS,
                credenciais: credenciais
            });
        }
    }, []);

    const efetuarLogin = (usuario, senha) => {
        Api.logarConta(usuario, senha)
        .then(function (token) {
            setIsLogin(false);
            store.dispatch({
                type: Actions.ADICIONAR_CREDENCIAIS,
                credenciais: token
            });
            localStorage.setItem('credenciais', token);
        })
        .catch(function (error) {
            console.log(error);
        });   
    };

    const efetuarCadastro = (usuario, senha) => {
        Api.criarConta(usuario, senha)
        .then(function (token) {
            setIsCadastro(false);
        })
        .catch(function (error) {
            console.log(error);
        });
    };

    const exibirHeader = () => {
        if (!isAutenticado) {
            return (
                <Row className="credenciamento-header py-2">
                    <Col className="ml-auto">
                        <Button className="auth-button mr-2" onClick={ () => setIsLogin(true) }>Login</Button>
                        <Button className="auth-button" onClick={ () => setIsCadastro(true) }>Cadastrar</Button>
                    </Col>
                </Row>
            );
        }
    };

    return (
        <>
            <ModalCredenciamento 
                show={isLogin}
                onHide={ 
                    () => { setIsLogin(false) } 
                }
                onSubmit={ efetuarLogin }
                submitText="Entrar"
            />

            <ModalCredenciamento 
                show={isCadastro}
                onHide={ 
                    () => { setIsCadastro(false) } 
                }
                onSubmit={ efetuarCadastro }
                submitText="Cadastrar"
            />
            { exibirHeader() }
        </>
    )
}
