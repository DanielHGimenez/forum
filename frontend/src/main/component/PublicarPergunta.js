import React, { useState, useEffect } from 'react';
import ModalPublicarPergunta from '../component/ModalPublicarPergunta';
import Button from '../component/Button';
import Api from '../service/Api';


export default function PublicarPergunta({ store, onPublicar }) {

    const [ isAutenticado, setIsAutenticado ] = useState(false);
    const [ isPublicarPergunta, setIsPublicarPergunta ] = useState(false);

    useEffect(() => {
        if (store.getState().credenciais != null) {
            setIsAutenticado(true);
        }
    }, []);

    store.subscribe(() => {
        if (store.getState().credenciais != null) {
            setIsAutenticado(true);
        } else {
            setIsAutenticado(false);
        }
    });

    const publicarPergunta = (pergunta) => {
        Api.publicarPergunta(store.getState().credenciais, pergunta)
        .then((response) => {
            onPublicar();
        })
        .catch((erro => {
            console.log(erro);
        }));
    };
    
    return (
        <>
            <ModalPublicarPergunta
                show={ isPublicarPergunta }
                onHide={ () => setIsPublicarPergunta(false) }
                onSubmit={ publicarPergunta }
            />
            {isAutenticado &&
                <Button className="publicar" onClick={ () => setIsPublicarPergunta(true) }>Publicar</Button>
            }
        </>
    )
}
