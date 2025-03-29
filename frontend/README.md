# Task manager

## Running


Run the following commands in your favourite shell.
```shell
git clone https://github.com/yaxley-peaks/task-manager.git
cd task-manager
```

and then

```shell
npm install
npm run build 
```

OR,


You may need to install node and npm; if you don't want to do that, use the docker build

```shell
export DOCKER_BUILDKIT=1; docker build -o dist .
```

This will put a `index.html` in the `dist` directory. Open that in your favourite browser.

## Component structure explanation

- `App.jsx`: Primary parent component, provides state.
  I would have used `useReducer`s but I was asked to use `useState` to manage state
- `AddTask`: Form to add a new task.
- `TaskItem`: Manages a single task. Editing and deleting.
- `TaskList`: Renders a list of `TaskItem`s.

## Bonus items implemented
- [X] Search bar
- [X] Dark mode (Use system setting).
- [X] Items persist in localstorage