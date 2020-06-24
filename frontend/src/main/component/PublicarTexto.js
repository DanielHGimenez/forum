import React, { useState, useEffect } from 'react';
import ModalPublicarTexto from '../component/ModalPublicarTexto';
import Button from './Button';
import '../style/PublicarTexto.css';


export default function PublicarTexto({ store, onSubmit, labelModal }) {

    const [ isAutenticado, setIsAutenticado ] = useState(false);
    const [ isPublicarTexto, setIsPublicarTexto ] = useState(false);

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

    const publicarTexto = (texto) => {
        setIsPublicarTexto(false);
        onSubmit(texto);
    };
    
    return (
        <>
            <ModalPublicarTexto
                show={ isPublicarTexto }
                onHide={ () => setIsPublicarTexto(false) }
                onSubmit={ publicarTexto }
                label={ labelModal }
            />
            {isAutenticado &&
                <Button className="Publicar-texto" onClick={ () => setIsPublicarTexto(true) }>Publicar</Button>
            }
        </>
    )
}
