
export default function rootReducer(state = {
    credenciais: null
}, action) {
    switch (action.type) {
        case "ADICIONAR_CREDENCIAIS":
            state.credenciais = action.credenciais;
            return state;
        case "LIMPAR_CREDENCIAIS":
            state.credenciais = null;
            return state;
        default:
            return state;
    }
}

var last =  function(array) {
    return array.slice(Math.max(array.length - 1, 0));  
};