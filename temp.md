ProjectManager{
- projects: ArrayList<Project>
}

Project{
- title: Title
- tasks: ArrayList<Task>
- label: Label
- repository: Repository
- graph: HashMap<Task, HashMap<Task, Integer>>
}

Task{
- title: Title
- startDay: Date
- endDay: Date
- assignments: ArrayList<People>
- status: Status
- labels: Label
- majorLabel: Major
- time: int
- dependentTasks: ArrayList<Task>
- degree: int
}

Date{
- day: int
- month: int
- year: int 
}

Label{
- type: String
- description: String
}

Major{
- MAJOR: String
- LEVEL: int
- majors: String[]
+ MAX: int
}

People{
- name: String
- id: String
- majors: ArrayList<Major>
- assignedTask: ArrayList<Task>
}

Repository{
- link: String
- title: String
}

Title{
- title: String
- description: String
}

