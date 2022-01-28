import _ from 'lodash'
import React from 'react'
import { Search, Button } from 'semantic-ui-react'
import Axios from "axios"
import Link from 'next/link';
import styles from '../../styles/Searchbar.module.css'

var source8: any

const source5 = Axios.get('http://makeup-api.herokuapp.com/api/v1/products.json?brand=maybelline')
.then(({data}) => {
  const source2 =  data.map( (content: any) => ({
    title: String(content.description),
    price: String(content.id),
    description: '#tag',
  }))
  source8 = source2
  return source2
})

const initialState = {
  loading: false,
  results: [],
  value: '',
}

function exampleReducer(state: any, action: { type: any; query: any; results: any; selection: any; }) {
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
      const isMatch = (result: any) => re.test(result.title)

      dispatch({
        type: 'FINISH_SEARCH',
        results: _.filter(source8, isMatch),
      })
    }, 300)
  }, [])
  React.useEffect(() => {
    return () => {
      clearTimeout(timeoutRef.current)
    }
  }, [])

  return (
    <>
        <Search
          loading={loading}
          onResultSelect={(e, data) =>
            dispatch({ type: 'UPDATE_SELECTION', selection: data.result.price })
          }
          onSearchChange={handleSearchChange}
          results={results}
          value={value}
          className={styles.search_location}
        />
        <div className={styles.button_style}>
          <Button><Link href={`/feed/personal/${value}`}><a>검색</a></Link></Button>
        </div>
        
    </>

  )
}

export default SearchExampleStandard