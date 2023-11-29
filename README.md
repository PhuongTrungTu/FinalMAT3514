<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/PhuongTrungTu/FinalMAT3514">
    <img src="image/logo.png" alt="Logo" width="100" height="100">
  </a>

<h2 align="center">Project Manager</h2>

  <p align="center">
    Mange your project for the best
    <br />
    <a href="https://github.com/othneildrew/Best-README-Template"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/othneildrew/Best-README-Template">View Demo</a>
    ·
    <a href="https://github.com/PhuongTrungTu/FinalMAT3514/issues">Report Bug</a>
    ·
    <a href="https://github.com/PhuongTrungTu/FinalMAT3514/issues">Request Feature</a>
  </p>
</div>

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

[![Product Name Screen Shot][product-screenshot]](https://example.com)

The task management application allows users to flexibly add, edit, and 
delete projects as well as tasks. Users can display information based on 
time, priority, task group, or board. It provides essential fields such 
as title, description, assignee, label, repository link, deadline date, 
and status. The ability to create custom fields reflects the specific 
characteristics of the project or task, creating a robust and flexible 
project management environment.

<p align="right">(<a href="#readme-top">back to top</a>)</p>



### Built With

This section should list any major frameworks/libraries used to bootstrap your project. Leave any add-ons/plugins for the acknowledgements section. Here are a few examples.

* [![Java](https://img.shields.io/badge/Java-%23ED8B00.svg?&style=for-the-badge&logo=java&logoColor=white)](https://www.java.com/)
* [![SQL](https://img.shields.io/badge/SQL-%23018BBB.svg?&style=for-the-badge&logo=sql&logoColor=white)](JQuery-url)
* [![Jackson ObjectMapper](https://img.shields.io/badge/Jackson_ObjectMapper-%23018BBB.svg?&style=for-the-badge)](https://github.com/FasterXML/jackson-databind)

<!-- GETTING STARTED -->
## Getting Started

Before start lets make sure you set up ```Jackson ObjectMapper``` to process json file.
You can read more about json process using ```Jackson ObjectMapper``` at [Intro to the Jackson ObjectMapper](https://www.baeldung.com/jackson-object-mapper-tutorial)

### Prerequisites
* Java
Make sure that change memory setting to at least 4GB.
Java version using in this project: ```java version: 19.0.2```

* Sql


* Jackson Object mapper

Let’s first add the following dependencies to the ```pom.xml```:
```
<dependencies>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.12.5</version>
        </dependency>
</dependencies>
```

This dependency will also transitively add the following libraries to the classpath:
* jackson-annotations
* jackson-core

Always use the latest versions from the Maven central repository for jackson-databind.

### Installation

_Below is an example of how you can instruct your audience on installing and setting up your app. This template doesn't rely on any external dependencies or services._

1. Get a free API Key at [https://example.com](https://example.com)
2. Clone the repo
   ```sh
   git clone https://github.com/PhuongTrungTu/FinalMAT3514.git
   ```
3. Install NPM packages
   ```sh
   npm install
   ```
4. Enter your API in `config.js`
   ```js
   const API_KEY = 'ENTER YOUR API';
   ```

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- USAGE EXAMPLES -->
## Usage

Use this space to show useful examples of how a project can be used. Additional screenshots, code examples and demos work well in this space. You may also link to more resources.

_For more examples, please refer to the [Documentation](https://example.com)_

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ROADMAP -->
## Roadmap

- [x] All structure using in project
- [x] Create components in Project
- [x] Writing and reading json file
- [x] Store data using ```Jackson ObjectMapper```
- [x] Create new project
- [x] Create new task for project
- [x] Search project
- [x] Delete project, delete task in project
- [ ] Change status of task in project
- [ ] Display in normal view (Table)
- [ ] Display task of project in the more near end day of each task
- [ ] The relationship between tasks
- [ ] Pathfinding algorithm
- [ ] Normal User interface
- [ ] Priority Level of each task
- [ ] Priority Level of each project
- [ ] Database for store big data
- [ ] Stack for operation (state at time k of manager)
- [x] manager display in console in normal mode


See the [open issues](https://github.com/PhuongTrungTu/FinalMAT3514/issues) for a full list of proposed features (and known issues).


<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE.txt` for more information.


<!-- CONTACT -->
## Contact

Hoàng Tuấn Tú (Grizmo) - [Hoàng Tú](https://www.facebook.com/tuantu2610/) - hoangtuantu893@gmail.com

Nguyễn Thị Hà Phương (Htmlexe) - [Nguyễn Phương](https://www.facebook.com/profile.php?id=100034060476971) - nguyenthihaphuong_t66@hus.edu.vn

Nguyễn Thành Trung (Mlisfi) - [Nguyễn Thành Trung](https://www.facebook.com/nguyenthanhtrung25) - nguyenthanhtrung_t66@hus.edu.vn

Project Link: [https://github.com/your_username/repo_name](https://github.com/your_username/repo_name)

<!-- ACKNOWLEDGMENTS -->
## Acknowledgments

Use this space to list resources you find helpful and would like to give credit to. I've included a few of my favorites to kick things off!

* [Choose an Open Source License](https://choosealicense.com)
* [GitHub Emoji Cheat Sheet](https://www.webpagefx.com/tools/emoji-cheat-sheet)
* [Malven's Flexbox Cheatsheet](https://flexbox.malven.co/)
* [Malven's Grid Cheatsheet](https://grid.malven.co/)
* [Img Shields](https://shields.io)
* [GitHub Pages](https://pages.github.com)
* [Font Awesome](https://fontawesome.com)
* [React Icons](https://react-icons.github.io/react-icons/search)

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/othneildrew/Best-README-Template.svg?style=for-the-badge
[contributors-url]: https://github.com/othneildrew/Best-README-Template/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/othneildrew/Best-README-Template.svg?style=for-the-badge
[forks-url]: https://github.com/othneildrew/Best-README-Template/network/members
[stars-shield]: https://img.shields.io/github/stars/othneildrew/Best-README-Template.svg?style=for-the-badge
[stars-url]: https://github.com/othneildrew/Best-README-Template/stargazers
[issues-shield]: https://img.shields.io/github/issues/othneildrew/Best-README-Template.svg?style=for-the-badge
[issues-url]: https://github.com/othneildrew/Best-README-Template/issues
[license-shield]: https://img.shields.io/github/license/othneildrew/Best-README-Template.svg?style=for-the-badge
[license-url]: https://github.com/othneildrew/Best-README-Template/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/othneildrew
[product-screenshot]: images/screenshot.png
[Next.js]: https://img.shields.io/badge/next.js-000000?style=for-the-badge&logo=nextdotjs&logoColor=white
[Next-url]: https://nextjs.org/
[React.js]: https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB
[React-url]: https://reactjs.org/
[Vue.js]: https://img.shields.io/badge/Vue.js-35495E?style=for-the-badge&logo=vuedotjs&logoColor=4FC08D
[Vue-url]: https://vuejs.org/
[Angular.io]: https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white
[Angular-url]: https://angular.io/
[Svelte.dev]: https://img.shields.io/badge/Svelte-4A4A55?style=for-the-badge&logo=svelte&logoColor=FF3E00
[Svelte-url]: https://svelte.dev/
[Laravel.com]: https://img.shields.io/badge/Laravel-FF2D20?style=for-the-badge&logo=laravel&logoColor=white
[Laravel-url]: https://laravel.com
[Bootstrap.com]: https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white
[Bootstrap-url]: https://getbootstrap.com
[JQuery.com]: https://img.shields.io/badge/jQuery-0769AD?style=for-the-badge&logo=jquery&logoColor=white
[JQuery-url]: https://jquery.com 
