import { CredenciaisActions as Actions } from './CredenciaisActions.js';

export default function rootReducer(state = {
    credenciais: null
}, action) {
    switch (action.type) {
        case Actions.ADICIONAR_CREDENCIAIS:
            state.credenciais = action.credenciais;
            return state;
        case Actions.LIMPAR_CREDENCIAIS:
            state.credenciais = null;
            return state;
        default:
            return state;
    }
}