-task({"build:erlang", "Compile merl sources"}).

run("build:erlang", _) ->
    tetrapak:outputcmd(tetrapak:dir(), "make", []).
