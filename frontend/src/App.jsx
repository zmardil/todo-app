import React, {useEffect, useState} from 'react'
import axios from 'axios'

const API_BASE = 'http://localhost:8080/api'

function App(){
  const [tasks, setTasks] = useState([])
  const [title, setTitle] = useState('')
  const [description, setDescription] = useState('')

  const load = async () => {
    const r = await axios.get(`${API_BASE}/tasks?limit=5`)
    setTasks(r.data)
  }

  useEffect(()=>{ load() }, [])

  const add = async (e)=>{
    e.preventDefault()
    if(!title) return
    await axios.post(`${API_BASE}/tasks`, {title, description})
    setTitle(''); setDescription('')
    load()
  }

  const done = async (id)=>{
    await axios.patch(`${API_BASE}/tasks/${id}/complete`)
    load()
  }

  return (
    <div className="container">
      <h1>Todo</h1>
      <form onSubmit={add} className="form">
        <input placeholder="Title" value={title} onChange={e=>setTitle(e.target.value)} />
        <textarea placeholder="Description" value={description} onChange={e=>setDescription(e.target.value)} />
        <button type="submit">Add</button>
      </form>

      <div className="list">
        {tasks.map(t=> (
          <div className="card" key={t.id}>
            <h3>{t.title}</h3>
            <p>{t.description}</p>
            <small>{new Date(t.createdAt).toLocaleString()}</small>
            <div>
              <button onClick={()=>done(t.id)}>Done</button>
            </div>
          </div>
        ))}
      </div>
    </div>
  )
}

export default App
