const express = require('express')
const app = express()
const port = 3000
const cors = require('cors')

const TokenUtil = require('./token');

app.use(express.json());
app.use(cors())

app.post('/login', (req, res) => {

    let user = req.body;

    console.log(user);

    payload = {};
    payload.roles = ['ESTUDANTE'];
    payload.id = null;
    payload.nome = user.login;

    if(user.login === 'rafa' && user.password === '123'){
        
        payload.id = 1;

    }else if(user.login === 'paula' && user.password === '123'){

        payload.id = 2;

    }else if(user.login === 'admin' && user.password === '123'){

        payload.roles.push('ADMIN');
    }else{

        return res.status(401).json({message: 'Login inválido!'});
    }
    let tokenGerado = new TokenUtil().criarToken(payload);
    console.log(tokenGerado);

    return res.json({ auth: true, token:  tokenGerado});
      
      
})


app.listen(port, () => {
  console.log(`Example app listening at http://localhost:${port}`)
})