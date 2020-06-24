
export default class StringUtil {

    static isBlank(str) {
        return str && !/^\s*$/.test(str);
    }

}