const apiBase = "/api";

async function createCategory() {
    const name = document.getElementById("categoryName").value;
    if (!name) return alert("Category name cannot be empty!");

    const res = await fetch(`${apiBase}/categories`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ name })
    });

    if (res.ok) {
        document.getElementById("categoryName").value = "";
        loadCategories();
    } else {
        alert("Error creating category");
    }
}

async function loadCategories() {
    const res = await fetch(`${apiBase}/categories`);
    const categories = await res.json();

    const select = document.getElementById("taskCategory");
    select.innerHTML = "";
    categories.forEach(cat => {
        const option = document.createElement("option");
        option.value = cat.id;
        option.textContent = cat.name;
        select.appendChild(option);
    });

    renderTaskTables(categories);
}

async function createTask() {
    const title = document.getElementById("taskTitle").value;
    const categoryId = parseInt(document.getElementById("taskCategory").value);
    if (!title) return alert("Task title cannot be empty!");

    const res = await fetch(`${apiBase}/tasks`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ title, categoryId })
    });

    if (res.ok) {
        document.getElementById("taskTitle").value = "";
        loadCategories();
    } else {
        alert("Error creating task");
    }
}

async function toggleTaskStatus(taskId, completed) {
    await fetch(`${apiBase}/tasks/${taskId}/status?completed=${completed}`, { method: "PUT" });
    loadCategories();
}

async function deleteTask(taskId) {
    await fetch(`${apiBase}/tasks/${taskId}`, { method: "DELETE" });
    loadCategories();
}

async function renderTaskTables(categories) {
    const container = document.getElementById("categoriesContainer");
    container.innerHTML = "";

    for (const cat of categories) {
        const res = await fetch(`${apiBase}/tasks/category/${cat.id}`);
        const tasks = await res.json();

        if (tasks.length === 0) continue;

        const table = document.createElement("table");
        table.className = "category-table";

        const thead = document.createElement("thead");
        thead.innerHTML = `<tr><th colspan="3">${cat.name}</th></tr>`;
        table.appendChild(thead);

        const tbody = document.createElement("tbody");
        tasks.forEach(task => {
            const tr = document.createElement("tr");
            tr.className = task.completed ? "completed" : "";
            tr.innerHTML = `
                <td>${task.title}</td>
                <td>
                    <button class="task-btn done-btn ${task.completed ? 'done' : ''}"
                            onclick="toggleTaskStatus(${task.id}, ${!task.completed})">
                        ${task.completed ? 'Mark Pending' : 'Mark Done'}
                    </button>
                </td>
                <td>
                    <button class="task-btn delete-btn" onclick="deleteTask(${task.id})">Delete</button>
                </td>
            `;
            tbody.appendChild(tr);
        });

        table.appendChild(tbody);
        container.appendChild(table);
    }
}

window.onload = loadCategories;
