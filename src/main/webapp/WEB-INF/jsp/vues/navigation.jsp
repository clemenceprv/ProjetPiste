<%--
  Created by IntelliJ IDEA.
  User: sachamontel
  Date: 05/05/2023
  Time: 08:04
  To change this template use File | Settings | File Templates.
--%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Permis piste</a>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">

        <form class="d-flex input-group w-auto">
            <input
                    type="search"
                    class="form-control rounded"
                    placeholder="Search"
                    aria-label="Search"
                    aria-describedby="search-addon"
            />
            <span class="input-group-text text-white border-0" id="search-addon">
        <i class="fas fa-search"></i>
      </span>
        </form>
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li>
                <a class="nav-item nav-link " href="/">Accueil</a>
            </li>
            <li><a class="nav-item nav-link" href="/mission/index">Missions</a></li>
            <li><a class="nav-item nav-link" href="/utilisateur/index">Apprenants</a></li>
            <li><a class="nav-item nav-link " href="#">Jeux</a></li>
            <li><a class="nav-item nav-link " href="/action/getAll">Actions</a></li>
            <li><a class="nav-item nav-link " href="/login/deconnection">Deconnection</a></li>
        </ul>
    </div>
</nav>
