import React from 'react';
import '../style/Button.css';

export default function Button(props) {
    
    return (
        <button className={"app-btn " + props.className} onClick={props.onClick}>
            {props.children}
        </button>
    );

}
