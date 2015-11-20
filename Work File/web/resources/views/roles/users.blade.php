@extends('app')

@section('content')

@foreach ($allusers as $index => $Users) 

	{{$Users->id}} {{$Users->name}}
@endforeach

@endsection