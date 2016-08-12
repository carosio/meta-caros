#!/usr/bin/env escript

main(Path) ->
  try
    {ok, OldProp}=file:consult(Path),
    removeDeps(OldProp,Path)
  catch
    error:_ ->
      {error, Error}=file:consult(Path),
      io:format("~s~n",[Error]),
      usage()
  end.

usage() ->
  io:format("remove specific version from rebar.config~nto build with any version of the dependencie~n"),
  halt(1).

checkUndefined(undefined) ->
  io:format("no dependencies found"),
  halt(0);
checkUndefined(_) ->
  io:format("dependencies found").

removeDeps(OldProp,Path) ->
  Deps = proplists:get_value(deps, OldProp),
  checkUndefined(proplists:get_value(deps, OldProp)),
  NewDeps = [ {X,Y} || {X,Y,_} <- Deps ],
  NewProp = proplists:delete(deps, OldProp),
  ReturnProp = NewProp ++ [{deps, NewDeps}],
  write_terms(Path,ReturnProp).

write_terms(Filename, List) ->
  Format = fun(Term) -> io_lib:format("~tp.~n", [Term]) end,
  Text = lists:map(Format, List),
  file:write_file(Filename, Text).
