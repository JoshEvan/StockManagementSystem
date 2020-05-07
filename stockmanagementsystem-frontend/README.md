# react-typescript-webpack-babel
first working project, tutor: https://www.udemy.com/share/101whABEMedVxRR3w=/


# React CONTEXT
4/1/2020

*react context bikin permudah untuk passing data dari parent compo ke child compo, kaya supaya bisa pass lgsg, ga hrs lwt intermideate compo antara yg nge pass dan di pass in ( kaya lgsg tusuk pass data nya)*

**context** return ada 
1. provider -> provide data ke device 
2. consumer -> yg consume data dari produser

contoh context:
**di file App.js**
```


//const Store = React.createContext()

// dengan destructuring sama dengan

const { Provider, Consumer } = React.createContext()

function Parent(props){
  const text = "random text";
  // pass property namanya value berisi text
  return <Provider value={text}>{props.children}</Provider>;
}

function Child(){
  return (<Consumer>
     {text => <div>{text}</div>}
  </Consumer> )
}

```
**di file index.js**
```
import React from "react";
import ReactDOM from "react-dom";
impoer { Parent, Child } from "./App";
import "./style.css";

const RootElement = document.getElementById("root");
ReactDOM.render(
  <Parent>
    <Child/>
  </Parent>,
  rootElement
);

```
## callback function 
*ini yg disebut callback function*
```
{text => <div>{text}</div>}
```

### modifikasi code diatas menggunakan react *hooks*
*jadi ga perlu ada consumer produser dan callback function*
**di file App.js**
```


//const Store = React.createContext()

// dengan destructuring sama dengan

const Store= React.createContext()

function Parent(props){
  const text = "random text";
  // pass property namanya value berisi text
  return <Store.Provider value={text}>{props.children}</Store.Provider>;
}

function Child(){
  const hook = React.useContext(Store)
  return <div> {hook} </div> ;
}

```
*bisa juga untuk object*
**di file App.js**
```


//const Store = React.createContext()

// dengan destructuring sama dengan

const Store= React.createContext()

function Parent(props){
  const obj = {text: "random text"};
  // pass property namanya value berisi text
  return <Store.Provider value={obj}>{props.children}</Store.Provider>;
}

function Child(){
  const hook = React.useContext(Store)
  return <div> {hook.text} </div> ;
}

```

# REDUX
![1*OLdS7KqIA_4f1RHu0-YtsQ.jpeg](https://miro.medium.com/max/2208/1*OLdS7KqIA_4f1RHu0-YtsQ.jpeg)

redux punya 3 main principle:
1. **actions**: *sesuatu yang mulai manipulasi dari store*, compo akan trigger suatu fgsi yg akan start *action*,
contoh: increment1, action nya akan ke *reducer* dan cari apa yg hrs dilakukan ke store

2. **reducers**: ttg apa operasi manipulasi yg dilakuin ke *store*, dan ubah *store*

3. **store** : cuman boleh ada 1 kalo di redux, *database untuk frontend, smpan semua info ttg project*, ini dibaca oleh *component*

4. **component**: itu ya yg biasa ada di react, ketiga nya berinteraksi dengan component

**alur**: component* trigger *action*, *action* simpan code dari manipulasi nya misal increment angka by 1, *action* akan tell ke *reducer*, *reducer* lakuin perubahannya ke *store*, *component* baca perubahan dari *store*

