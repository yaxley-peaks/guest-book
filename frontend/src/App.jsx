import './App.css'
import {AddTask} from "./components/AddTask.jsx";
import {useEffect, useRef, useState} from "react";
import {TaskList} from "./components/TaskList.jsx";


function App() {
    const [tasks, setTasks] = useState(JSON.parse(localStorage.getItem('tasks')) ?? []);
    const [searchText, setSearchText] = useState("");
    const latestTaskId = useRef(0);

    useEffect(() => {
        const storedTasks = localStorage.getItem('tasks');
        if (storedTasks) {
            const ts = JSON.parse(storedTasks)
            setTasks(ts);
            // we reverse the array when api fetch, but save it the correct way around
            latestTaskId.current = ts.at(0)?.id ?? 1;
            return;
        }
        (async () => {
            /*
            @type {List<any>}
            */
            const fetchedTasks = await fetch("https://jsonplaceholder.typicode.com/todos")
                .then(res => res.json());
            // for now
            const tasks = fetchedTasks.slice(0, 2)
            latestTaskId.current = tasks.at(-1).id;
            setTasks(tasks.reverse());
        })();

    }, []);

    useEffect(() => {
        localStorage.setItem("tasks", JSON.stringify(tasks));
    }, [tasks]);

    const taskAdderCallback = (title, status) => {
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
