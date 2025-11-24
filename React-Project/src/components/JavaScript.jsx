import React from 'react'

const text = "Hello, JSX";
const num = 100;
const loginUser ={
    name: "최지원",
    id: "jiwon1004",
    age: 19,
    info: "안녕하세요.",
}

const JavaScript = () => {
  return (
    <>
        <h1>React의 JS</h1>
        <h2>변수 표현 방법</h2>
        <ul>
            <li>{text}</li>
            <li>{text + " TEST"}</li>
        </ul>
        <ul>
            <li>{num}</li>
            <li>{num + 900}</li>
        </ul>
        <h2>Boolean 값</h2>
        <ul>
            <li>{true}</li>
            <li>{false}</li>
            <li>{null}</li>
            <li>{undefined}</li>
        </ul>

        <h2>Object, Array</h2>
        <ul>
            {
                // [name,id,age,info]
                Object.keys(loginUser).map((key) => {
                    return <li> {key} : {loginUser[key]};</li>
                })
            }
        </ul>
    </>
  )
}

export default JavaScript