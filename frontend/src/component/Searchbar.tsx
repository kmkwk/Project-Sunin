import _ from 'lodash'
// import faker from 'faker'
import React from 'react'
import { Search, Grid, Header, Segment } from 'semantic-ui-react'
import Axios from "axios"
import { useEffect, useState } from 'react';

const source = _.times(1, () => ({
  title: 1,
  description: '123',
  image: null,
  price: 50,
  // title: faker.company.companyName(),
  // description: faker.company.catchPhrase(),
  // image: faker.internet.avatar(),
  // price: faker.finance.amount(0, 100, 2, '$'),
}))

// const source3 = [
//   {
//     title: '1abcdefg',
//     description: '123',
//     price: 50,
//   },
//   {
//     title: '2hijklmn',
//     description: '456',
//     price: 50,
//   },
//   {
//     title: '3opqrstu',
//     description: '789',
//     price: 50,
//   },
// ]

const source3 = async() => {
  await Axios.get('http://makeup-api.herokuapp.com/api/v1/products.json?brand=maybelline')
  .then(({ data }) => {
    // console.log(data)
    const source2 =  data.map( (content) => ({
      title: content.description,
      price: content.id,
      description: '#tag',
    }))
    console.log(source2)
    return source2
  })
}

const initialState = {
  loading: false,
  results: [],
  value: '',
}

function exampleReducer(state, action) {
  switch (action.type) {
    case 'CLEAN_QUERY':
      return initialState
    case 'START_SEARCH':
      return { ...state, loading: true, value: action.query }
    case 'FINISH_SEARCH':
      return { ...state, loading: false, results: action.results }
    case 'UPDATE_SELECTION':
      return { ...state, value: action.selection }

    default:
      throw new Error()
  }
}

function SearchExampleStandard() {
  // const [list, setList] = useState([]);

  // const API_URL = "http://makeup-api.herokuapp.com/api/v1/products.json?brand=maybelline";

  // function getData() {
  //   Axios.get(API_URL)
  //   .then(res => {
  //     console.log(res.data)
  //     setList(res.data)
  //   })
  // }

  // useEffect(()=>{
  //   getData();
  // }, []);

  // const source2 = list.map( (content) => ({
  //   title: content.description,
  //   price: content.id,
  //   description: '#tag',
  // }))
  // console.log(source3())
  const source4 = source3()
  console.log(source4)

  const [state, dispatch] = React.useReducer(exampleReducer, initialState)
  const { loading, results, value } = state

  const timeoutRef = React.useRef()
  const handleSearchChange = React.useCallback((e, data) => {
    clearTimeout(timeoutRef.current)
    dispatch({ type: 'START_SEARCH', query: data.value })

    timeoutRef.current = setTimeout(() => {
      if (data.value.length === 0) {
        dispatch({ type: 'CLEAN_QUERY' })
        return
      }

      const re = new RegExp(_.escapeRegExp(data.value), 'i')
      const isMatch = (result) => re.test(result.title)

      dispatch({
        type: 'FINISH_SEARCH',
        results: _.filter(source4, isMatch),
      })
    }, 300)
  }, [])
  React.useEffect(() => {
    return () => {
      clearTimeout(timeoutRef.current)
    }
  }, [])

  return (
    // <Grid>
    //   <Grid.Column width={6}>
        <Search
          loading={loading}
          onResultSelect={(e, data) =>
            dispatch({ type: 'UPDATE_SELECTION', selection: data.result.title })
          }
          onSearchChange={handleSearchChange}
          results={results}
          value={value}
        />
    //   </Grid.Column>

    //   <Grid.Column width={10}>
    //     <Segment>
    //       <Header>State</Header>
    //       <pre style={{ overflowX: 'auto' }}>
    //         {JSON.stringify({ loading, results, value }, null, 2)}
    //       </pre>
    //       <Header>Options</Header>
    //       <pre style={{ overflowX: 'auto' }}>
    //         {JSON.stringify(source, null, 2)}
    //       </pre>
    //     </Segment>
    //   </Grid.Column>
    // </Grid>
  )
}

export default SearchExampleStandard