import React from 'react'
import { Container, Row, Col } from 'react-bootstrap';
import { Link } from "react-router-dom";
import '../style/Pergunta.css';

export default function Pergunta({ link = false, limit=false, id, texto, quantidadeRespostas }) {
    return (
        <Container className="px-2 py-3 Pergunta">
            <Row className="mb-2">
                <Col className="d-flex">
                    {link ?
                        <Link className={ limit ? "limit texto" : "texto" } to={ "/perguntas/" + id }>
                            { texto }
                        </Link>
                        : texto
                    }
                    
                </Col>
            </Row>
            <Row className="about">
                <Col className="ml-2">
                    Quantidade de respostas: { quantidadeRespostas }
                </Col>
            </Row>
        </Container>
    )
}
