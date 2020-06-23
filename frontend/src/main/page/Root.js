import React, { useState, useEffect } from 'react';
import { createStore } from "redux";
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link
} from "react-router-dom";
import '../style/Root.css';
import Principal from './Principal';
import Pergunta from './Pergunta';
import Button from '../component/Button';
import ModalCredenciamento from '../component/ModalCredenciamento';
import Api from '../service/Api';
import Crendenciais from '../reducer/Credenciais';


export default function Root() {

    const store = createStore(Crendenciais);

    const [ isLogin, setIsLogin ] = useState(false);
    const [ isCadastro, setIsCadastro ] = useState(false);
    
    const [ token, setToken ] = useState(null);

    const efetuarLogin = (usario, senha) => {
        Api.logarConta(usuario, senha)
        .then(function (token) {
            fecharModal();
            setToken(token);
        })
        .catch(function (error) {
            console.log(error);
        });   
    };

    const efetuarCadastro = (usario, senha) => {
        Api.criarConta(usuario, senha)
        .then(function (token) {
            fecharModal();
            setToken(token);
        })
        .catch(function (error) {
            console.log(error);
        });   
    };

    return (
        <Container fluid className="Root">

            <ModalCredenciamento 
            show={isLogin}
            onHide={ 
                () => { setIsLogin(false) } 
            }
            onSubmit={ efetuarLogin }
            submitText="Entrar" />

            <ModalCredenciamento 
            show={isCadastro}
            onHide={ 
                () => { setIsCadastro(false) } 
            }
            onSubmit={ efetuarCadastro }
            submitText="Cadastrar" />

            {
                !token ?
                    
                : null
            }

            <Row>
                <Router>
                    <Switch>
                        <Route path="/" exact>
                            <Principal />
                        </Route>
                        <Route path="/perguntas/:id" children={<Pergunta />} />
                    </Switch>
                </Router>
            </Row>
        </Container>
    )
}
