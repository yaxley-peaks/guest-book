import './App.css'
import {AddTask} from "./components/AddTask.jsx";
import {useEffect, useRef, useState} from "react";
import {TaskList} from "./components/TaskList.jsx";


function App() {
    const [tasks, setTasks] = useState([]);
    const [searchText, setSearchText] = useState("");
    const latestTaskId = useRef(0);

    const fetchUrl = import.meta.env.VITE_BASE_API_URL;

    useEffect(() => {
        (async () => {
            /*
            @type {List<any>}
            */
            const fetchedTasks = await fetch(`${fetchUrl}/todos/`)
                .then(res => res.json());
            latestTaskId.current = fetchedTasks.at(-1).id;
            setTasks(fetchedTasks.reverse());
        })();

    }, [fetchUrl]);

    useEffect(() => {
        // post data to server here
    }, [tasks]);

    const taskAdderCallback = (title, status) => {
        (async () => {
            const request = await fetch(`${fetchUrl}/todos/`, {
                method: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    title: title,
                    completed: status === 1,
                    id: 0,
                    userId: 1,
                })
            });
            const json = await request.json();
            setTasks([json, ...tasks]);
        })();
        const newId = latestTaskId.current + 1;
        latestTaskId.current = newId;
        setTasks((tasks) => [{id: newId, title: title, completed: status === "1"}, ...tasks]);
    }
    const taskEditCallback = (id, title) => {
        tasks.find(x => x.id === id).title = title;
        let t = tasks.slice();
        t.at(t.findIndex(x => x.id === id)).title = title;
        setTasks(t);
    }
    const taskDeleteCallback = (id) => {
        setTasks(tasks.filter(x => x.id !== id))
    }
    const taskDoneCallback = (id, isDone) => {
        let t = tasks.slice();
        t.at(t.findIndex(x => x.id === id)).completed = isDone;
        setTasks(t);
    }

    return (<div className="grid grid-cols-1 gap-4">
        <h1 className="text-center font-sans">Task Manager</h1>

        <AddTask callbackFn={taskAdderCallback}/>

        <input className="p-2 m-2 w-full max-w-full rounded-lg" type="text" value={searchText} placeholder="Search..."
               onChange={(e) => {
                   setSearchText(e.target.value);
               }}/>

        <TaskList tasks={tasks.filter(t => t.title.toLowerCase().includes(searchText.toLowerCase()))}
                  taskEditDispatcher={taskEditCallback}
                  taskDeleteDispatcher={taskDeleteCallback}
                  taskDoneDispatcher={taskDoneCallback}/>
    </div>)
}

export default App
