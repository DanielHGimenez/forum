import React from 'react'
import { Pagination } from 'react-bootstrap';
import '../style/Paginacao.css'

export default function Paginacao({ paginaAtual, totalPaginas, onClick, className }) {
    return (
        <>
        {totalPaginas > 0 &&
            <Pagination className={ "Paginacao " + className }>
                {paginaAtual >= 3 && 
                    <Pagination.Item onClick={ () => onClick(1) }>1</Pagination.Item>
                }
                {paginaAtual >= 4 &&
                    <Pagination.Ellipsis />
                }
                {paginaAtual > 1 &&
                    <Pagination.Item onClick={ () => onClick(paginaAtual - 1) }>{ paginaAtual - 1 }</Pagination.Item>
                }
                
                <Pagination.Item active>{ paginaAtual }</Pagination.Item>
                
                {paginaAtual !== totalPaginas && 
                    <Pagination.Item onClick={ () => onClick(paginaAtual + 1) }>{ paginaAtual + 1 }</Pagination.Item>
                }
                {paginaAtual < totalPaginas - 2 &&
                    <Pagination.Ellipsis />
                }
                {paginaAtual <= totalPaginas - 2 &&
                    <Pagination.Item onClick={ () => onClick(totalPaginas) }>{ totalPaginas }</Pagination.Item>
                }
            </Pagination>
        }
        </>
    )
}
