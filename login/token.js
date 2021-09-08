const jwt = require('jsonwebtoken');

class TokenUtil {
  constructor() {
    //utilizado para assinar o TOKEN
    this._SECRET = '12345';
    this._TEMPO_EXPIRACAO = 86400;
  }

  criarToken(payload) {
    return jwt.sign(payload, this._SECRET, {
      expiresIn: this._TEMPO_EXPIRACAO, // expiração em 1 dia
    });
  }
}
module.exports = TokenUtil; 
